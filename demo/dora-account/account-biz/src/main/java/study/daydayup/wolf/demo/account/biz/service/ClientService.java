package study.daydayup.wolf.demo.account.biz.service;

import study.daydayup.wolf.demo.account.api.dto.ClientDTO;

public interface ClientService {
    
    ClientDTO getClient(String clientId);
}
