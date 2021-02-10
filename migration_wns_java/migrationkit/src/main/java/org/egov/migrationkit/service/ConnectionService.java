package org.egov.migrationkit.service;

import java.util.List;
import java.util.Map;

import org.egov.migrationkit.constants.WSConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.client.model.Address;
import io.swagger.client.model.Connection.StatusEnum;
import io.swagger.client.model.Demand;
import io.swagger.client.model.Locality;
import io.swagger.client.model.ProcessInstance;
import io.swagger.client.model.Property;
import io.swagger.client.model.RequestInfo;
import io.swagger.client.model.SewerageConnection;
import io.swagger.client.model.SewerageConnectionRequest;
import io.swagger.client.model.SewerageConnectionResponse;
import io.swagger.client.model.WaterConnection;
import io.swagger.client.model.WaterConnectionRequest;
import io.swagger.client.model.WaterConnectionResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class ConnectionService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@JsonProperty("host")
	@Value("${egov.services.hosturl}")
	private String host = null;

	@Value("${egov.services.water.url}")
	private String waterUrl = null;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PropertyService propertyService;
	@Autowired
	private RecordService recordService;
	
	@Autowired
	private DemandService demandService;
	
	@Value("${egov.services.sewerage.url}")
	private String sewerageUrl = null;


	public void migrateWtrConnection(String tenantId, RequestInfo requestInfo) {

	    recordService.initiate(tenantId);
		
		String searchPath = jdbcTemplate.queryForObject("show search_path",String.class);	
		log.info(searchPath);
		 
		List<String> queryForList = jdbcTemplate.queryForList(Sqls.waterQuery, String.class);

		for (String json : queryForList) {

			try {
				
				
				//IS_EXTERNAL_WORKFLOW_ENABLED
				Map data = objectMapper.readValue(json, Map.class);
				
			//	WaterConnection connection = mapWaterConnection(data);
				WaterConnection connection=	objectMapper.readValue(json, WaterConnection.class);
				/**
				 * Setting If consumer number is null then, assigning application number of ERP. 
				 */
				String connectionNo = connection.getConnectionNo() != null ? connection.getConnectionNo() : (String) data.get("applicationnumber");
				connection.setConnectionNo(connectionNo);
				
				log.info("mobile number : "+connection.getMobilenumber());
				log.info("getApplicantname ; "+connection.getApplicantname());
				log.info("connectionNo; "+connection.getConnectionNo());
				log.info("Connection Category : "+connection.getConnectionCategory());
				log.info("Connection Type :"+connection.getConnectionType());
				
				List<Map> roadCategoryList = (List<Map>) data.get("road_category");
				if (roadCategoryList != null) {
					String roadCategory = (String) roadCategoryList.get(0).get("road_name");
					connection.setRoadType(WSConstants.DIGIT_ROAD_CATEGORIES.get(roadCategory));
					try {
						Integer area = (Integer) roadCategoryList.get(0).get("road_area");

						connection.setRoadCuttingArea(Double.valueOf(area));
					} catch (Exception e) {
						Double area = (Double) roadCategoryList.get(0).get("road_area");
						connection.setRoadCuttingArea(area);
					}  

				}
					String addressQuery=	Sqls.address;
					Integer id =(Integer) data.get("applicantaddress.id");
					addressQuery=addressQuery.replace(":id",id.toString() );
				@SuppressWarnings("deprecation")
				Address address = (Address) jdbcTemplate.queryForObject(addressQuery,new BeanPropertyRowMapper(Address.class));  
				
				Locality locality = new Locality();
				// locality.setCode((String)data.get("locality"));
				// use the map here
				String localityCode = findLocality((String) data.get("locality"));
				if (localityCode == null) {
					recordService.recordError("water","Error In finding Locality", connection.getId());
					continue;
				}

				locality.setCode(localityCode);
				address.setLocality(locality);
				connection.setStatus(StatusEnum.Active);
				connection.setConnectionNo((String)data.get("consumercode"));
				connection.setApplicationStatus("CONNECTION_ACTIVATED");
			 
				connection.setApplicantAddress(address);

				connection.setTenantId(requestInfo.getUserInfo().getTenantId());
				connection.setProcessInstance(ProcessInstance.builder().action("INITIATE").build());
				
				recordService.recordWaterMigration(connection);
 				
				WaterConnectionRequest waterRequest = new WaterConnectionRequest();
				
				waterRequest.setWaterConnection(connection);
				waterRequest.setRequestInfo(requestInfo);
				Property property = propertyService.findProperty(waterRequest,json);
				if(property==null)
					continue;
				connection.setPropertyId(property.getId()); 

				String ss = "{" + requestInfo + ", \"waterConnection\": " + waterRequest + " }";

				log.info("request: " + ss);

				String response=null;
				try {
					response = restTemplate.postForObject(host + "/" + waterUrl, waterRequest, String.class);
				} catch (RestClientException e) {
					log.error(e.getMessage(),e);
					continue;
				}

				log.info("Response=" + response);

				WaterConnectionResponse waterResponse=	objectMapper.readValue(response, WaterConnectionResponse.class);
		       
				WaterConnection wtrConnResp = null;
				if(waterResponse!=null && waterResponse.getWaterConnection() != null 
						&& !waterResponse.getWaterConnection().isEmpty()) {
					
					wtrConnResp = waterResponse.getWaterConnection().get(0);
					
					String consumerCode = wtrConnResp.getConnectionNo() !=null ? wtrConnResp.getConnectionNo()
							: wtrConnResp.getApplicationNo();
					
					List<Demand> demandRequestList = demandService.prepareDemandRequest(data, WSConstants.WATER_BUSINESS_SERVICE, 
							consumerCode, requestInfo.getUserInfo().getTenantId(), property.getOwners().get(0));
					if(!demandRequestList.isEmpty()) {
						
						Boolean isDemandCreated = demandService.saveDemand(requestInfo, demandRequestList);
						if(isDemandCreated){
							Boolean isBillCreated = demandService.fetchBill(demandRequestList, requestInfo);

							
						}
						
						
						recordService.updateWaterMigration(wtrConnResp);
						log.info("waterResponse" + waterResponse);                                
					
				}
				}
				

			} catch (JsonMappingException e) {
				log.error(e.getMessage());
			} catch (JsonProcessingException e) {
				log.error(e.getMessage()); 
			}

		}
		log.info("Migration completed for "+tenantId);
	}

	private String getRequestInfoString() {

		StringBuffer buf = new StringBuffer(1000);

		return "";

	}
	
	private String findLocality(String code) {
		log.info("Seraching  for digit locality maping " + code);
		String digitcode = null;
		try {
			digitcode = jdbcTemplate.queryForObject("select digitcode as digitCode from finallocation where code=?",
					new Object[] { code }, String.class);
		} catch (DataAccessException e) {
			log.error("digit Location code is not mapped for " + code);
			digitcode = "SUN97";
		}
		log.info("returning  " + digitcode);
		return digitcode;
	}


	public void migratev2(String tenantId, RequestInfo requestInfo) {
		 
		
	}
	
	 
	
	public void createSewerageConnection(String tenantId, RequestInfo requestInfo) {

		jdbcTemplate.execute("set search_path to " + tenantId);
		
		jdbcTemplate.execute(Sqls.SEWERAGE_CONNECTION_TABLE);

		List<String> queryForList = jdbcTemplate.queryForList(Sqls.sewerageQuery, String.class);

		for (String json : queryForList) {
			
			try {
				
				Map data = objectMapper.readValue(json, Map.class);
				
			//	WaterConnection connection = mapWaterConnection(data);
			
				SewerageConnection connection2=	objectMapper.readValue(json, SewerageConnection.class);
				
//				List<Map> roadCategoryList = (List<Map>) data.get("road_category");
//				if (roadCategoryList != null) {
//					String roadCategory = (String) roadCategoryList.get(0).get("road_name");
//					connection.setRoadType(WSConstants.DIGIT_ROAD_CATEGORIES.get(roadCategory));
//					connection.setRoadCuttingArea(Float.valueOf((Integer)roadCategoryList.get(0).get("road_area")));
//
//				}
//					String addressQuery=	Sqls.address;
//					Integer id =(Integer) data.get("applicantaddress.id");
//					addressQuery=addressQuery.replace(":id",id.toString() );
//				@SuppressWarnings("deprecation")
//				Address address = (Address) jdbcTemplate.queryForObject(addressQuery,new BeanPropertyRowMapper(Address.class));  
//				
//				Locality locality=new Locality();
//				//locality.setCode((String)data.get("locality"));
//				//use the map here 
//				locality.setCode("ALOC4");
//				address.setLocality(locality);
//				
			 
				//connection.setApplicantAddress(address);

				connection2.setTenantId(requestInfo.getUserInfo().getTenantId());
				connection2.setProcessInstance(ProcessInstance.builder().action("INITIATE").build());
				
				//recordService.recordWaterMigration(connection);
 				
				SewerageConnectionRequest sewerageRequest = new SewerageConnectionRequest();
				
				sewerageRequest.setSewerageConnection(connection2);
				sewerageRequest.setRequestInfo(requestInfo);
				Property property = propertyService.findProperty(sewerageRequest,json);
				//connection2.setPropertyId(property.getId()); 
				//Search the connection value
				if(property==null)
					continue;
				connection2.setPropertyId(property.getId()); 
				//String ss = "{" + requestInfo + ", \"waterConnection\": " + sewerageRequest + " }";

				//log.info("request: " + ss);

				String response = restTemplate.postForObject(host + "/" + sewerageUrl, sewerageRequest, String.class);

				//log.info("Response=" + response);

				SewerageConnectionResponse sewerageResponse=	objectMapper.readValue(response, SewerageConnectionResponse.class);
		       
				SewerageConnection srgConnResp = null;
				if(srgConnResp!=null && sewerageResponse.getSewerageConnections() != null 
						&& !sewerageResponse.getSewerageConnections().isEmpty()) {
					
					srgConnResp = sewerageResponse.getSewerageConnections().get(0);
					
					String consumerCode = srgConnResp.getConnectionNo() !=null ? srgConnResp.getConnectionNo()
							: srgConnResp.getApplicationNo();
					
					List<Demand> demandRequestList = demandService.prepareDemandRequest(data, WSConstants.SEWERAGE_BUSINESS_SERVICE, 
							consumerCode, requestInfo.getUserInfo().getTenantId(), property.getOwners().get(0));
					if(!demandRequestList.isEmpty()) {
						
						Boolean isDemandCreated = demandService.saveDemand(requestInfo, demandRequestList);
						if (isDemandCreated) {
							Boolean isBillCreated = demandService.fetchBill(demandRequestList, requestInfo);
						}
						recordService.updateSewerageMigration(srgConnResp);
						log.info("sewerageResponse" + sewerageResponse);                                
					
				}

			}
				

			} catch (JsonMappingException e) {
				log.error(e.getMessage());
			} catch (JsonProcessingException e) {
				log.error(e.getMessage()); 
			}

		}
		log.info("Sewarage Connection Created for "+tenantId);

			
	}
	
}
