package com.hexaware.insurancemanagementsystem.service;

import com.hexaware.insurancemanagementsystem.entity.Policy;
import com.hexaware.insurancemanagementsystem.exception.PolicyNotFoundException;
import java.util.List;

public interface IPolicyService {
    boolean createPolicy(Policy policy);
    Policy getPolicy(int policyId) throws PolicyNotFoundException;
    List<Policy> getAllPolicies();
    boolean updatePolicy(Policy policy) throws PolicyNotFoundException;
    boolean deletePolicy(int policyId) throws PolicyNotFoundException;
}
