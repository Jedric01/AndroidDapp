package com.example.socialdapp.contracts;

import android.util.Log;

import com.example.socialdapp.contracts.Dapp;

import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;

public class Contract {
    public static Dapp contract;
    public static Credentials userCredentials;
    private static final BigInteger GAS_LIMIT=  BigInteger.valueOf(5000000L);
    private static final BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    private static final String RECIPIENT = "0x588F7954eDD06E776dA49648483D4fF8Bcf67133";
    private static String contractAddress = "0xd28e77ed19d54b4dedae306ea000c86e1521ec5b";

    public static Web3j getWeb3jInstance(){
        return Web3j.build(new HttpService("http://10.0.2.2:8545/"));
    }

    public static void DeployContract(Web3j web3j){
        String contractAddress = null;
        try {
            contractAddress = Dapp.deploy(web3j, userCredentials, GAS_PRICE, GAS_LIMIT)
                    .sendAsync().get().getContractAddress();
            Log.d("web3Version", "deployed Contract address: " + contractAddress);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("web3Version", "Failed to Deploy Contract");
        }
    }

    public static void loadContract(Web3j web3j){
        contract =  Dapp.load(contractAddress, web3j, userCredentials, GAS_PRICE, GAS_LIMIT);
    }

    public static void getCredentialsFromPrivateKey(String PRIVATE_KEY){
        userCredentials =  Credentials.create(PRIVATE_KEY);
    }
}
