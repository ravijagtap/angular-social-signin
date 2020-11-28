package com.windmillsmartsolutions.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.windmillsmartsolutions.utils.AuthEntryConstant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtils {

	@Autowired
	GoogleTokenUtil googleUtil;
	
	/*private final String AUDIENCE_WEB = "web";
	private final String AUDIENCE_MOBILE = "mobile";
	private final String AUDIENCE_TABLET = "tablet";
	
	private String secret = "#kj478*&#DB78494unfp94idsjfkdskfjsdfnk@*^&*kjnjbkdfjs";
	
	private final Long expiration = 10800L;
	
	public String getUsernameFromToken (String token) {
		String username;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}
	
	public Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			created = new Date((Long)claims.get("created"));
		} catch (Exception e) {
			created = null;
		}
		return created;
	}
	
	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}
	
	public String getAudienceFromToken (String token) {
		String audience;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			audience = (String) claims.get("audience");
		} catch (Exception e) {
			audience = null;
		}
		return audience;
	}
	
	private Claims getClaimsFromToken (String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(this.secret)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}
	
	private Date generateCurrentDate(){
		return new Date(System.currentTimeMillis());
	}
	
	private Date generateExpirationDate () {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = this.getExpirationDateFromToken(token);
		return expiration.before(this.generateCurrentDate());
	}
	
	public String generateToken(SpringSecurityUser userDetails) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("sub", userDetails.getUsername());
		claims.put("name", userDetails.getName());
		claims.put("ormbUserId", userDetails.getOrmbUserId());
		claims.put("personId", userDetails.getPersonId());
		claims.put("currency", userDetails.getCurrency());
		claims.put("audience", AUDIENCE_WEB);
		claims.put("created", this.generateCurrentDate());
		
		return this.generateToken(claims);
	}
	
	private String generateToken(Map<String, Object> claims) {
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(this.generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, this.secret)
				.compact();
	}
	
	public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
		Date createdDate = this.getCreatedDateFromToken(token);
		return (!(this.isCreatedBeforeLastPasswordReset(createdDate, lastPasswordReset))
				&& (!(isTokenExpired(token)) || this.ignoreTokenExpiration(token)));
	}
	
	public String refreshToken (String token) {
		String refreshToken;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			claims.put("created", this.generateCurrentDate());
			refreshToken = this.generateToken(claims);
		} catch (Exception e) {
			// TODO: handle exception
			refreshToken = null;
		}
		return refreshToken;
	}
	
	private Boolean ignoreTokenExpiration(String token) {
		String audience = this.getAudienceFromToken(token);
		return (this.AUDIENCE_TABLET.equals(audience) || this.AUDIENCE_MOBILE.equals(audience));
	}
	
	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

	public Boolean validateToken(String token, UserDetails userDetails) {
        SpringSecurityUser user = (SpringSecurityUser) userDetails;
        final String username = this.getUsernameFromToken(token);
        final Date created = this.getCreatedDateFromToken(token);        
        return (username.equals(user.getUsername())
                && !(this.isTokenExpired(token))
                && !(this.isCreatedBeforeLastPasswordReset(created, user.getLastPasswordReset())));
    }

	public String getUsernameFromToken(String authToken, String provider) {
		String username;
		if(AuthEntryConstant.GOOGLE.equals(provider)) {
			username = googleUtil.getUser(authToken);
		} else {
			try {
				final Claims claims = this.getClaimsFromToken(authToken);
				username = claims.getSubject();
			} catch (Exception e) {
				username = null;
			}
		}
		
		return username;
	}*/
	
}
