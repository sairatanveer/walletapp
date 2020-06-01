package com.saira.walletapp.Service;

import com.saira.walletapp.enitity.Wallet;
import com.saira.walletapp.exception.WalletException;
import com.saira.walletapp.reporsitory.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    private WalletRepository walletRepository;
    public List<Wallet> getAll()
    {
        return walletRepository.findAllByOrOrderByPriority();
    }
    public Wallet getById(Long id)
    {
        Optional<Wallet> wallet= walletRepository.findById(id);
        if(wallet.isPresent())
        {
            return wallet.get();

        }
        throw  new WalletException("Wallet with "+id+"does not exists!");
    }

    public Wallet createOrUpdate(Wallet wallet) {
        if (wallet.getId() == null) {
            walletRepository.save(wallet);
        } else {
            walletRepository.save(wallet);
        }
        return wallet;
    }
    public boolean delete(Long id)
    {
        Optional<Wallet> wallet= walletRepository.findById(id);
        if(wallet.isPresent())
        {
            walletRepository.delete(wallet.get());
            return true;
        }
throw  new WalletException("Wallet with "+id+"does not exists!");
    }
}
