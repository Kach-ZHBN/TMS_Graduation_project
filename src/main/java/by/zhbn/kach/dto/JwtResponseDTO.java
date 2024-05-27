package by.zhbn.kach.dto;

public class JwtResponseDTO {
    private String accessToken;

    public JwtResponseDTO() {
    }

    public JwtResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
