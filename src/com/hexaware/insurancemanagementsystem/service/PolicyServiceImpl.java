package com.hexaware.insurancemanagementsystem.service;

import com.hexaware.insurancemanagementsystem.dao.IPolicyDao;
import com.hexaware.insurancemanagementsystem.dao.PolicyDaoImpl;
import com.hexaware.insurancemanagementsystem.entity.Policy;
import com.hexaware.insurancemanagementsystem.exception.PolicyNotFoundException;

import java.util.List;

public class PolicyServiceImpl implements IPolicyService {

    private IPolicyDao policyDao = new PolicyDaoImpl();

    @Override
    public boolean createPolicy(Policy policy) {
        boolean success = policyDao.createPolicy(policy);
        if (!success) {
            System.err.println("Error: Unable to create policy in the database.");
        }
        return success;
    }

    @Override
    public Policy getPolicy(int policyId) throws PolicyNotFoundException {
        Policy policy = policyDao.getPolicy(policyId);
        if (policy == null) {
            throw new PolicyNotFoundException("Policy with ID " + policyId + " does not exist.");
        }
        return policy;
    }

    @Override
    public List<Policy> getAllPolicies() {
        return policyDao.getAllPolicies();
    }

    @Override
    public boolean updatePolicy(Policy policy) throws PolicyNotFoundException {
        Policy existing = policyDao.getPolicy(policy.getPolicyId());
        if (existing == null) {
            throw new PolicyNotFoundException("Cannot update: Policy with ID " + policy.getPolicyId() + " not found.");
        }
        boolean success = policyDao.updatePolicy(policy);
        if (!success) {
            System.err.println("Error: Failed to update policy.");
        }
        return success;
    }

    @Override
    public boolean deletePolicy(int policyId) throws PolicyNotFoundException {
        Policy existing = policyDao.getPolicy(policyId);
        if (existing == null) {
            throw new PolicyNotFoundException("Cannot delete: Policy with ID " + policyId + " not found.");
        }
        boolean success = policyDao.deletePolicy(policyId);
        if (!success) {
            System.err.println("Error: Failed to delete policy.");
        }
        return success;
    }
}
