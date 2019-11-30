package com.zhy.java.ddd.wallet;

import java.math.BigDecimal;

/**
 * 领域模型设计-钱包系统
 */
public class VirtualWallet {
    private Long id;
    private Long createTime = System.currentTimeMillis();
    private BigDecimal balance = BigDecimal.ZERO;
    private boolean isAllowedOverdraft = true;
    private BigDecimal overdraftAmount = BigDecimal.ZERO;
    private BigDecimal frozenAmount = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId){
        this.id = preAllocatedId;
    }

    public BigDecimal getBalance(){
        return this.balance;
    }

    public BigDecimal getAvaliableBalance(){
        BigDecimal totalAvaliableBalance = this.balance.subtract(this.frozenAmount);
        if(isAllowedOverdraft){
            totalAvaliableBalance = totalAvaliableBalance.add(this.overdraftAmount);
        }
        return totalAvaliableBalance;
    }

    public void debit(BigDecimal amount){
        BigDecimal totalAvaliableBalance = getAvaliableBalance();
        if(totalAvaliableBalance.compareTo(amount) < 0){
            throw new RuntimeException("余额不足");
        }
        this.balance = this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("金额必须大于等于0");
        }
        this.balance = this.balance.add(amount);
    }
}
