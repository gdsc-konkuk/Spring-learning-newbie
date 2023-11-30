package gdsc.shine.springlearningsimple.config.environment;

public class JwtTokenKeyProvider2 {
    private String secretKey;

    public JwtTokenKeyProvider2(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
