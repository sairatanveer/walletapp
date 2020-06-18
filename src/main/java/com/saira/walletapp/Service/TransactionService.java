package com.saira.walletapp.Service;

import com.saira.walletapp.enitity.Transaction;
import com.saira.walletapp.enitity.Wallet;
import com.saira.walletapp.exception.WalletException;
import com.saira.walletapp.reporsitory.TransactionRepository;
import com.saira.walletapp.reporsitory.WalletRepository;
import jdk.internal.jline.internal.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    public WalletRepository walletRepository;
    public List<Transaction> getAll(Long walletId)
    {
        Optional<Wallet> wallet = walletRepository.findById(walletId);
if(wallet.isPresent()){
    return transactionRepository.findByWallet(wallet.get());

}
return null;
    }
  public Transaction getById(Long wallet_id,Long id) {
      Optional<Wallet> wallet = walletRepository.findById(wallet_id);
      if (wallet.isPresent()) {
          Optional<Transaction> transaction = transactionRepository.findById(id);
          if (transaction.isPresent()) {
              return transaction.get();

      }
          throw new WalletException("Transaction with " + id + "does not exists!");
      }
      return null;
  }

    public Transaction createOrUpdate(Long walletId, Transaction transaction) {
        Optional<Wallet> wallet = walletRepository.findById(walletId);
        if (wallet.isPresent()) {
            transaction.setWallet(wallet.get());
            transactionRepository.save(transaction);
            return transaction;
        }
        return null;
    }
  public boolean delete(Long wallet_id,Long id)
  {
      Optional<Wallet> wallet = walletRepository.findById(wallet_id);
      if (wallet.isPresent()) {
          Optional<Transaction> transaction = transactionRepository.findById(id);
          if (transaction.isPresent()) {
              transactionRepository.delete(transaction.get());
              return true;
          }
      }
        throw  new WalletException("Transaction with "+id+"does not exists!");
   }
}

