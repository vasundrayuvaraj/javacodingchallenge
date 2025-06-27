package com.hexaware.insurancemanagementsystem.dao;

import com.hexaware.insurancemanagementsystem.entity.Policy;
import java.util.List;

public interface IPolicyDao {
    boolean createPolicy(Policy policy);
    Policy getPolicy(int policyId);
    List<Policy> getAllPolicies();
    boolean updatePolicy(Policy policy);
    boolean deletePolicy(int policyId);
}
