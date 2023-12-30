package org.example.security.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.body.RequestBodyEntity;
import org.apache.http.Header;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.example.model.TokenDetail;
import org.example.security.dao.LoginDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

//@Service
public class GenerateToken {
//
//
//    @Value("${security.oauth2-server-url}")
//    private  String authServer;
//
//    @Value("${security.jwt.grant-type-password}")
//    private  String grantType_Password;
//
//    @Value("${security.jwt.grant-type-refresh_token}")
//    private  String grantType_RefreshToken;
//
//    @Value("${security.oauth2-server.user-id}")
//    private  String client_id;
//
//    @Value("${security.oauth2-server.password}")
//    private  String client_secret;
//
//    @Value("${security.refresh-token-url}")
//    private String refresh_token_url;
//
////    @Autowired
////    private TokenRepo tokenRepo;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
////    @Autowired
////    private UserRepository userRepo;
//
//    public TokenDetail generateToken(TokenDetail tokenDetail, LoginDao login) {
//        JSONObject jsonObj = new JSONObject(getOauthAccessToken(login).getBody().toString());
//        String access_token = jsonObj.get("access_token").toString();
//        String refresh_token = jsonObj.get("refresh_token").toString();
//        String token_type = jsonObj.get("token_type").toString();
//        String scope = jsonObj.get("scope").toString();
//        String expires_in = jsonObj.get("expires_in").toString();
////        tokenDetail.setToken(access_token);
////        tokenDetail.setRefreshToken(refresh_token);
//        //tokenDetail.setTokenExpiryTime(Integer.valueOf(expires_in));
//        return tokenDetail;
//    }
//
//    private ResponseEntity getOauthAccessToken(LoginDao login) {
//        final CloseableHttpClient client = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(authServer);
//        UsernamePasswordCredentials creds = new UsernamePasswordCredentials(client_id, client_secret);
//        Header header = null;
//        String responseBody = null;
//        int responseCode = -1;
//        try {
//            header = new BasicScheme().authenticate(creds, httpPost, null);
//            httpPost.addHeader(header);
//            UserDetails userDetails;
//            RequestBodyEntity request = Unirest.post(authServer)
//                    .basicAuth("BEY_OAuth2_Client", "Test@123")
//                    .header("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
//                    .header(header.getName(), header.getValue())
//                    .body("------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"grant_type\"\r\n\r\n"+ grantType_Password + "\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"username\"\r\n\r\n" + login.getUserName()+"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"password\"\r\n\r\n" +login.getPassword() +"\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
//            HttpResponse<String> response = request.asString();
//            responseBody = response.getBody();
//            responseCode = response.getStatus();
//            client.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return new ResponseEntity(responseBody, HttpStatus.valueOf(responseCode));
//    }






}
