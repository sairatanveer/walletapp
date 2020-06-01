package com.saira.walletapp.reporsitory;

import com.saira.walletapp.enitity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository <Wallet,Long> {
List<Wallet> findAllByOrOrderByPriority();
}
