package com.alkemy.wallet.fixedterm.repository;

import com.alkemy.wallet.fixedterm.domain.FixedTermDeposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedDepositRepository extends JpaRepository<FixedTermDeposit, Long> {
}
