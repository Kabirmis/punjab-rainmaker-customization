/*
 * eGov Water and Sewerage(W&S)  System.
 * APIs for W&S module. This provide APIs for create new property, update existing property, search existing property. 
 *
 * OpenAPI spec version: 1.0.0
 * Contact: contact@egovernments.org
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.api;

import io.swagger.client.ApiCallback;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;
import io.swagger.client.ProgressRequestBody;
import io.swagger.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import java.math.BigDecimal;
import io.swagger.client.model.ErrorRes;
import io.swagger.client.model.RequestInfo;
import io.swagger.client.model.ResponseInfo;
import io.swagger.client.model.WaterConnectionRequest;
import io.swagger.client.model.WaterConnectionResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WaterApi {
    private ApiClient apiClient;

    public WaterApi() {
        this(Configuration.getDefaultApiClient());
    }

    public WaterApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for wcCancelPost
     * @param body Request header for the property delete Request. (required)
     * @param tenantId Unique id for a tenant. (required)
     * @param propertyId The properrtyId to be deactivated (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call wcCancelPostCall(RequestInfo body, String tenantId, String propertyId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = body;
        
        // create path and map variables
        String localVarPath = "/wc/_cancel";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (tenantId != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("tenantId", tenantId));
        if (propertyId != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("propertyId", propertyId));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "*/*"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call wcCancelPostValidateBeforeCall(RequestInfo body, String tenantId, String propertyId, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException("Missing the required parameter 'body' when calling wcCancelPost(Async)");
        }
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new ApiException("Missing the required parameter 'tenantId' when calling wcCancelPost(Async)");
        }
        // verify the required parameter 'propertyId' is set
        if (propertyId == null) {
            throw new ApiException("Missing the required parameter 'propertyId' when calling wcCancelPost(Async)");
        }
        
        com.squareup.okhttp.Call call = wcCancelPostCall(body, tenantId, propertyId, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Deactivate existing water connection.
     * Deactivate existing water connection.
     * @param body Request header for the property delete Request. (required)
     * @param tenantId Unique id for a tenant. (required)
     * @param propertyId The properrtyId to be deactivated (required)
     * @return ResponseInfo
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ResponseInfo wcCancelPost(RequestInfo body, String tenantId, String propertyId) throws ApiException {
        ApiResponse<ResponseInfo> resp = wcCancelPostWithHttpInfo(body, tenantId, propertyId);
        return resp.getData();
    }

    /**
     * Deactivate existing water connection.
     * Deactivate existing water connection.
     * @param body Request header for the property delete Request. (required)
     * @param tenantId Unique id for a tenant. (required)
     * @param propertyId The properrtyId to be deactivated (required)
     * @return ApiResponse&lt;ResponseInfo&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ResponseInfo> wcCancelPostWithHttpInfo(RequestInfo body, String tenantId, String propertyId) throws ApiException {
        com.squareup.okhttp.Call call = wcCancelPostValidateBeforeCall(body, tenantId, propertyId, null, null);
        Type localVarReturnType = new TypeToken<ResponseInfo>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Deactivate existing water connection. (asynchronously)
     * Deactivate existing water connection.
     * @param body Request header for the property delete Request. (required)
     * @param tenantId Unique id for a tenant. (required)
     * @param propertyId The properrtyId to be deactivated (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call wcCancelPostAsync(RequestInfo body, String tenantId, String propertyId, final ApiCallback<ResponseInfo> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = wcCancelPostValidateBeforeCall(body, tenantId, propertyId, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ResponseInfo>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for wcCreatePost
     * @param body Details for the new property + RequestHeader meta data. (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call wcCreatePostCall(WaterConnectionRequest body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = body;
        
        // create path and map variables
        String localVarPath = "/wc/_create";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "*/*"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call wcCreatePostValidateBeforeCall(WaterConnectionRequest body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException("Missing the required parameter 'body' when calling wcCreatePost(Async)");
        }
        
        com.squareup.okhttp.Call call = wcCreatePostCall(body, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Apply for new water connection.
     * Citizen or employee can apply for new water connection. 
     * @param body Details for the new property + RequestHeader meta data. (required)
     * @return WaterConnectionResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public WaterConnectionResponse wcCreatePost(WaterConnectionRequest body) throws ApiException {
        ApiResponse<WaterConnectionResponse> resp = wcCreatePostWithHttpInfo(body);
        return resp.getData();
    }

    /**
     * Apply for new water connection.
     * Citizen or employee can apply for new water connection. 
     * @param body Details for the new property + RequestHeader meta data. (required)
     * @return ApiResponse&lt;WaterConnectionResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<WaterConnectionResponse> wcCreatePostWithHttpInfo(WaterConnectionRequest body) throws ApiException {
        com.squareup.okhttp.Call call = wcCreatePostValidateBeforeCall(body, null, null);
        Type localVarReturnType = new TypeToken<WaterConnectionResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Apply for new water connection. (asynchronously)
     * Citizen or employee can apply for new water connection. 
     * @param body Details for the new property + RequestHeader meta data. (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call wcCreatePostAsync(WaterConnectionRequest body, final ApiCallback<WaterConnectionResponse> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = wcCreatePostValidateBeforeCall(body, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<WaterConnectionResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for wcDeletePost
     * @param body Request header for the connection delete Request. (required)
     * @param tenantId Unique id for a tenant. (required)
     * @param connectionNo The connection no to be deactivated (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call wcDeletePostCall(RequestInfo body, String tenantId, String connectionNo, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = body;
        
        // create path and map variables
        String localVarPath = "/wc/_delete";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (tenantId != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("tenantId", tenantId));
        if (connectionNo != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("connectionNo", connectionNo));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "*/*"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call wcDeletePostValidateBeforeCall(RequestInfo body, String tenantId, String connectionNo, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException("Missing the required parameter 'body' when calling wcDeletePost(Async)");
        }
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new ApiException("Missing the required parameter 'tenantId' when calling wcDeletePost(Async)");
        }
        // verify the required parameter 'connectionNo' is set
        if (connectionNo == null) {
            throw new ApiException("Missing the required parameter 'connectionNo' when calling wcDeletePost(Async)");
        }
        
        com.squareup.okhttp.Call call = wcDeletePostCall(body, tenantId, connectionNo, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Delete existing water connection.
     * Delete existing water connection.
     * @param body Request header for the connection delete Request. (required)
     * @param tenantId Unique id for a tenant. (required)
     * @param connectionNo The connection no to be deactivated (required)
     * @return ResponseInfo
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ResponseInfo wcDeletePost(RequestInfo body, String tenantId, String connectionNo) throws ApiException {
        ApiResponse<ResponseInfo> resp = wcDeletePostWithHttpInfo(body, tenantId, connectionNo);
        return resp.getData();
    }

    /**
     * Delete existing water connection.
     * Delete existing water connection.
     * @param body Request header for the connection delete Request. (required)
     * @param tenantId Unique id for a tenant. (required)
     * @param connectionNo The connection no to be deactivated (required)
     * @return ApiResponse&lt;ResponseInfo&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ResponseInfo> wcDeletePostWithHttpInfo(RequestInfo body, String tenantId, String connectionNo) throws ApiException {
        com.squareup.okhttp.Call call = wcDeletePostValidateBeforeCall(body, tenantId, connectionNo, null, null);
        Type localVarReturnType = new TypeToken<ResponseInfo>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Delete existing water connection. (asynchronously)
     * Delete existing water connection.
     * @param body Request header for the connection delete Request. (required)
     * @param tenantId Unique id for a tenant. (required)
     * @param connectionNo The connection no to be deactivated (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call wcDeletePostAsync(RequestInfo body, String tenantId, String connectionNo, final ApiCallback<ResponseInfo> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = wcDeletePostValidateBeforeCall(body, tenantId, connectionNo, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ResponseInfo>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for wcSearchPost
     * @param body RequestHeader meta data. (required)
     * @param tenantId Unique id for a tenant. (required)
     * @param ids List of system generated ids of water connection. (optional)
     * @param connectionNo List of water connection numbers to search.. (optional)
     * @param oldConnectionNo List of old water connection numbers to search.. (optional)
     * @param mobileNumber MobileNumber of owner whose water connection is to be searched. (optional)
     * @param fromDate Fetches properties with created time after fromDate. (optional)
     * @param toDate Fetches properties with created time till toDate. (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call wcSearchPostCall(RequestInfo body, String tenantId, List<String> ids, List<String> connectionNo, List<String> oldConnectionNo, Long mobileNumber, BigDecimal fromDate, BigDecimal toDate, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = body;
        
        // create path and map variables
        String localVarPath = "/wc/_search";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (tenantId != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("tenantId", tenantId));
        if (ids != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("multi", "ids", ids));
        if (connectionNo != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("multi", "connectionNo", connectionNo));
        if (oldConnectionNo != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("multi", "oldConnectionNo", oldConnectionNo));
        if (mobileNumber != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("mobileNumber", mobileNumber));
        if (fromDate != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("fromDate", fromDate));
        if (toDate != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("toDate", toDate));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "*/*"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call wcSearchPostValidateBeforeCall(RequestInfo body, String tenantId, List<String> ids, List<String> connectionNo, List<String> oldConnectionNo, Long mobileNumber, BigDecimal fromDate, BigDecimal toDate, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException("Missing the required parameter 'body' when calling wcSearchPost(Async)");
        }
        // verify the required parameter 'tenantId' is set
        if (tenantId == null) {
            throw new ApiException("Missing the required parameter 'tenantId' when calling wcSearchPost(Async)");
        }
        
        com.squareup.okhttp.Call call = wcSearchPostCall(body, tenantId, ids, connectionNo, oldConnectionNo, mobileNumber, fromDate, toDate, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Get the list of exsting water connections.
     * Get the water connections list based on the input parameters. 
     * @param body RequestHeader meta data. (required)
     * @param tenantId Unique id for a tenant. (required)
     * @param ids List of system generated ids of water connection. (optional)
     * @param connectionNo List of water connection numbers to search.. (optional)
     * @param oldConnectionNo List of old water connection numbers to search.. (optional)
     * @param mobileNumber MobileNumber of owner whose water connection is to be searched. (optional)
     * @param fromDate Fetches properties with created time after fromDate. (optional)
     * @param toDate Fetches properties with created time till toDate. (optional)
     * @return WaterConnectionResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public WaterConnectionResponse wcSearchPost(RequestInfo body, String tenantId, List<String> ids, List<String> connectionNo, List<String> oldConnectionNo, Long mobileNumber, BigDecimal fromDate, BigDecimal toDate) throws ApiException {
        ApiResponse<WaterConnectionResponse> resp = wcSearchPostWithHttpInfo(body, tenantId, ids, connectionNo, oldConnectionNo, mobileNumber, fromDate, toDate);
        return resp.getData();
    }

    /**
     * Get the list of exsting water connections.
     * Get the water connections list based on the input parameters. 
     * @param body RequestHeader meta data. (required)
     * @param tenantId Unique id for a tenant. (required)
     * @param ids List of system generated ids of water connection. (optional)
     * @param connectionNo List of water connection numbers to search.. (optional)
     * @param oldConnectionNo List of old water connection numbers to search.. (optional)
     * @param mobileNumber MobileNumber of owner whose water connection is to be searched. (optional)
     * @param fromDate Fetches properties with created time after fromDate. (optional)
     * @param toDate Fetches properties with created time till toDate. (optional)
     * @return ApiResponse&lt;WaterConnectionResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<WaterConnectionResponse> wcSearchPostWithHttpInfo(RequestInfo body, String tenantId, List<String> ids, List<String> connectionNo, List<String> oldConnectionNo, Long mobileNumber, BigDecimal fromDate, BigDecimal toDate) throws ApiException {
        com.squareup.okhttp.Call call = wcSearchPostValidateBeforeCall(body, tenantId, ids, connectionNo, oldConnectionNo, mobileNumber, fromDate, toDate, null, null);
        Type localVarReturnType = new TypeToken<WaterConnectionResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get the list of exsting water connections. (asynchronously)
     * Get the water connections list based on the input parameters. 
     * @param body RequestHeader meta data. (required)
     * @param tenantId Unique id for a tenant. (required)
     * @param ids List of system generated ids of water connection. (optional)
     * @param connectionNo List of water connection numbers to search.. (optional)
     * @param oldConnectionNo List of old water connection numbers to search.. (optional)
     * @param mobileNumber MobileNumber of owner whose water connection is to be searched. (optional)
     * @param fromDate Fetches properties with created time after fromDate. (optional)
     * @param toDate Fetches properties with created time till toDate. (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call wcSearchPostAsync(RequestInfo body, String tenantId, List<String> ids, List<String> connectionNo, List<String> oldConnectionNo, Long mobileNumber, BigDecimal fromDate, BigDecimal toDate, final ApiCallback<WaterConnectionResponse> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = wcSearchPostValidateBeforeCall(body, tenantId, ids, connectionNo, oldConnectionNo, mobileNumber, fromDate, toDate, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<WaterConnectionResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for wcUpdatePost
     * @param body Request of water connection details. (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call wcUpdatePostCall(WaterConnectionRequest body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = body;
        
        // create path and map variables
        String localVarPath = "/wc/_update";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "*/*"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call wcUpdatePostValidateBeforeCall(WaterConnectionRequest body, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException("Missing the required parameter 'body' when calling wcUpdatePost(Async)");
        }
        
        com.squareup.okhttp.Call call = wcUpdatePostCall(body, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Update existing water connection details.
     * Updates a given &#x60;water connection&#x60; with newer details.
     * @param body Request of water connection details. (required)
     * @return WaterConnectionResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public WaterConnectionResponse wcUpdatePost(WaterConnectionRequest body) throws ApiException {
        ApiResponse<WaterConnectionResponse> resp = wcUpdatePostWithHttpInfo(body);
        return resp.getData();
    }

    /**
     * Update existing water connection details.
     * Updates a given &#x60;water connection&#x60; with newer details.
     * @param body Request of water connection details. (required)
     * @return ApiResponse&lt;WaterConnectionResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<WaterConnectionResponse> wcUpdatePostWithHttpInfo(WaterConnectionRequest body) throws ApiException {
        com.squareup.okhttp.Call call = wcUpdatePostValidateBeforeCall(body, null, null);
        Type localVarReturnType = new TypeToken<WaterConnectionResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Update existing water connection details. (asynchronously)
     * Updates a given &#x60;water connection&#x60; with newer details.
     * @param body Request of water connection details. (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call wcUpdatePostAsync(WaterConnectionRequest body, final ApiCallback<WaterConnectionResponse> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = wcUpdatePostValidateBeforeCall(body, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<WaterConnectionResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}