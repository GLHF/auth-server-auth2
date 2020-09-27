package com.craftsmengroup.paymentsystem.authserver.secure.details;

import com.craftsmengroup.paymentsystem.authserver.model.Client;
import com.craftsmengroup.paymentsystem.authserver.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomClientDetailsService implements ClientDetailsService {
    private final ClientRepository clientRepository;

    @Value(("#{new Integer('${access_token.valid_seconds}')}"))
    private int validSecondsAccess;
    @Value(("#{new Integer('${refresh_token.valid_seconds}')}"))
    private int validSecondsRefresh;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client =
                clientRepository.findByClientId(clientId).orElseThrow(() -> new ClientRegistrationException(String.format("Client with ClientId %s not found", clientId)));
        BaseClientDetails baseClientDetails = new BaseClientDetails(client.getClientId(), null, client.getScopes(),
                client.getAuthorizedGrandTypes(), client.getAuthorities());
        baseClientDetails.setClientSecret(client.getSecret());
        baseClientDetails.setAccessTokenValiditySeconds(validSecondsAccess);
        baseClientDetails.setRefreshTokenValiditySeconds(validSecondsRefresh);
        baseClientDetails.setRegisteredRedirectUri(Collections.singleton("http://localhost:8081"));
        return baseClientDetails;
    }
}
