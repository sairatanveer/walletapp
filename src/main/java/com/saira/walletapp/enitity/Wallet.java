package com.saira.walletapp.enitity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.IMessage;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wallet {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   @NotBlank(message= "Name cann't be blank")
   @Size(min=2,max=30)
    private String name;
    @Size(max=30)
    private String accountNumber;
    @Size(max=100)
    private String Description;
    @Min(1)
    @Max(3)
    private Integer priority;
    private Double currentBalance;
    @OneToMany(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY,mappedBy = "wallet",orphanRemoval = true)
   @JsonIgnore
    private List<Transaction>transactions;
@PrePersist
    public void setBalance(){ this.currentBalance = new Double(0);}
}
