package study.daydayup.wolf.demo.account.biz.authorization.facade;

import study.daydayup.wolf.demo.account.api.dto.ClientDTO;
import study.daydayup.wolf.demo.account.biz.authorization.vo.ClientVO;
import study.daydayup.wolf.demo.account.biz.service.ClientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ClientFacade {

    @Resource
    private ClientService clientService;


    public ClientVO getClient(String clientId) {
        ClientDTO clientDTO = null;
        try {
            clientDTO = clientService.getClient(clientId);
            return new ClientVO(clientDTO.getClientId());
        } catch (Exception e) {
        }
        return null;
    }

}
