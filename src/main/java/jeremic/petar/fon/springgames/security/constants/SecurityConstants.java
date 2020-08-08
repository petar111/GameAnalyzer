package jeremic.petar.fon.springgames.security.constants;

public class SecurityConstants {

    public static final String[] AUTH_WHITELIST = {"/","/login","/auth/login","/home/creature/update"};
    public static final String JWT_SECRET_KEY = "7817586165658315776978987486471-391568161597189390820941=02592498195";
    public static final long EXPIRATION_TIME = 432_000_000;
    public static final String JWT_ISSUER = "SIUX";
    public static final String JWT_VERIFIER_ERROR = "Token can not be verified";
    public static final String JWT_AUTHORITIES = "authorities";
    public static final String JWT_AUDIENCE = "Audience FIXME";
    public static final String JWT_TOKEN_PREFIX = "Bearer ";
    public static final String JWT_OPTIONS = "OPTIONS";
    public static final String FORBIDDEN_MESSAGE = "You need to login to access this content.";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this content.";

    private SecurityConstants(){

    }
}
