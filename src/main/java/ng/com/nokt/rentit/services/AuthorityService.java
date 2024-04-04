package ng.com.nokt.rentit.services;

import ng.com.nokt.rentit.entities.Authority;
import ng.com.nokt.rentit.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    public Authority save(Authority authority){
        return authorityRepository.save(authority);
    }

    public Optional<Authority> findById(Long id){
        return authorityRepository.findById(id);
    }
}
