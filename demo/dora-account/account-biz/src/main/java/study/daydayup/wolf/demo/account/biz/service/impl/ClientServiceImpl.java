package study.daydayup.wolf.demo.account.biz.service.impl;

import study.daydayup.wolf.demo.account.api.dto.ClientDTO;
import study.daydayup.wolf.demo.account.api.enums.ClientEnum;
import study.daydayup.wolf.demo.account.biz.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {
    
    @Override
    public ClientDTO getClient(String clientId) {
        ClientEnum clientEnum = ClientEnum.getClientEnumById(clientId);
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setClientId(clientEnum.getId());
        return clientDTO;
    }
}
