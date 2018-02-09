package com.wdzsj.cmn.helper;

import com.wdzsj.cmn.util.DateUtil;
import com.wdzsj.cmn.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.lang.Assert;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

public class UserTokenHelper {
	
	private static Key key = generateKey(SignatureAlgorithm.HS256);
			
	public static Long getUserId(String token) {
		try {
			if(StringUtil.isEmpty(token)) return null;
		
			Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
			return Long.valueOf(claims.getSubject());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getToken(long userId) {
		Date expDate = DateUtil.parseDate("2018-12-31");//new Date(new Date().getTime() + 3000*24*60*60*1000);//失效1天
		return Jwts.builder()
				  .setSubject(String.valueOf(userId))
				  .setExpiration(expDate)
				  .signWith(SignatureAlgorithm.HS512, key)
				  .compact();
	}
	
	private static SecretKey generateKey(SignatureAlgorithm alg) {
        Assert.isTrue(alg.isHmac(), "SignatureAlgorithm argument must represent an HMAC algorithm.");
        byte[] bytes;
        switch (alg) {
            case HS256:
                bytes = new byte[32];
                break;
            case HS384:
                bytes = new byte[48];
                break;
            default:
                bytes = new byte[64];
        }
        return new SecretKeySpec(bytes, alg.getJcaName());
    }
	
	public static void main(String[] args) {
        String token = UserTokenHelper.getToken(2);
		System.out.println(token);
		System.out.println(token.length());
		
        Long obj = UserTokenHelper
            .getUserId("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzOCIsImV4cCI6MTU0NjE4NTYwMH0.Q-ve7OzdvjcDxA-_-ndbgElP16fgB-8E2Wf5wfloD6FEPOSidZJihNMzE25Ohsuj2zxHgpow3obRgimYMPVrMQ");
		System.out.println(obj);
	}
}
