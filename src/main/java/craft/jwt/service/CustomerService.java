package craft.jwt.service;

import craft.jwt.model.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final List<CustomerDTO> customerDTOList;

    public CustomerService() {

        customerDTOList = new ArrayList<>();

        customerDTOList.add(CustomerDTO.builder().customerId(1).name("Pedro").email("pedro@mail.es").build());
        customerDTOList.add(CustomerDTO.builder().customerId(2).name("Julia").email("julia@mail.es").build());
        customerDTOList.add(CustomerDTO.builder().customerId(3).name("Sandra").email("Sandra@mail.es").build());

    }

    public CustomerDTO getCustomerById(Integer customerId){

        return customerDTOList
                .stream()
                .filter(c -> c.getCustomerId().intValue() == customerId)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Customer not found"));

    }

}
