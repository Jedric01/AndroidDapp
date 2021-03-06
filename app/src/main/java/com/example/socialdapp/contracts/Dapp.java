package com.example.socialdapp.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.6.0.
 */
public class Dapp extends Contract {
    private static final String BINARY = "6080604052600060035534801561001557600080fd5b5033600081815260208181526040808320601490556003805460018181019092558452909152902080546001600160a01b03191690911790556107a08061005d6000396000f3fe6080604052600436106100915760003560e01c8063704f1b9411610059578063704f1b941461021057806370a08231146102c35780637efff887146102f65780639114ad5c14610300578063f8b2cb4f1461034657610091565b806307973ccf146100965780630cf79e0a146100bd5780630e666e4914610101578063365b98b21461013457806365f68c89146101dd575b600080fd5b3480156100a257600080fd5b506100ab610379565b60408051918252519081900360200190f35b3480156100c957600080fd5b506100ed600480360360408110156100e057600080fd5b508035906020013561037f565b604080519115158252519081900360200190f35b34801561010d57600080fd5b506100ed6004803603602081101561012457600080fd5b50356001600160a01b0316610420565b34801561014057600080fd5b5061015e6004803603602081101561015757600080fd5b503561046d565b6040518083815260200180602001828103825283818151815260200191508051906020019080838360005b838110156101a1578181015183820152602001610189565b50505050905090810190601f1680156101ce5780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b3480156101e957600080fd5b506100ab6004803603602081101561020057600080fd5b50356001600160a01b0316610522565b34801561021c57600080fd5b506100ed6004803603602081101561023357600080fd5b81019060208101813564010000000081111561024e57600080fd5b82018360208201111561026057600080fd5b8035906020019184600183028401116401000000008311171561028257600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610567945050505050565b3480156102cf57600080fd5b506100ab600480360360208110156102e657600080fd5b50356001600160a01b031661063b565b6102fe61064d565b005b34801561030c57600080fd5b5061032a6004803603602081101561032357600080fd5b5035610685565b604080516001600160a01b039092168252519081900360200190f35b34801561035257600080fd5b506100ab6004803603602081101561036957600080fd5b50356001600160a01b03166106a0565b60035481565b600061038a33610420565b15806103b457506000838152600160205260409020546103b2906001600160a01b0316610420565b155b156103be57600080fd5b336000908152602081905260409020548211156103dd5750600061041a565b5033600090815260208181526040808320805485900390558483526001808352818420546001600160a01b03168452918390529091208054830190555b92915050565b6000805b600354811015610462576000818152600160205260409020546001600160a01b038481169116141561045a576001915050610468565b600101610424565b50600090505b919050565b6002818154811061047a57fe5b60009182526020918290206002918202018054600180830180546040805193821615610100026000190190911695909504601f81018790048702830187019095528482529195509193919290918301828280156105185780601f106104ed57610100808354040283529160200191610518565b820191906000526020600020905b8154815290600101906020018083116104fb57829003601f168201915b5050505050905082565b6000805b600354811015610561576000818152600160205260409020546001600160a01b0384811691161415610559579050610468565b600101610526565b50919050565b600061057233610420565b1561057f57506000610468565b60038054600090815260016020818152604080842080546001600160a01b03191633179055805180820190915284548084019095559384528381018681526002805493840180825594819052855193027f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5ace81019384559051805194959461062f937f405787fa12a823e0f2b7631cc41b3ba8828b3321ca811111fa75cd3aa3bb5acf9093019291909101906106d0565b50600195945050505050565b60006020819052908152604090205481565b61065633610420565b61065f57600080fd5b603234101561066d57600080fd5b33600090815260208190526040902080546005019055565b6001602052600090815260409020546001600160a01b031681565b60006106ab82610420565b6106b457600080fd5b506001600160a01b031660009081526020819052604090205490565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061071157805160ff191683800117855561073e565b8280016001018555821561073e579182015b8281111561073e578251825591602001919060010190610723565b5061074a92915061074e565b5090565b61076891905b8082111561074a5760008155600101610754565b9056fea265627a7a72315820cb734c08558d8c897d8f489644321fa9a4657038af7c5bcb27920e109072483564736f6c63430005110032";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_GETID = "getId";

    public static final String FUNC_IDMAP = "idMap";

    public static final String FUNC_REGISTERUSER = "registerUser";

    public static final String FUNC_TOPUPBALANCE = "topUpBalance";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_USERCOUNT = "userCount";

    public static final String FUNC_USEREXISTS = "userExists";

    public static final String FUNC_USERS = "users";

    @Deprecated
    protected Dapp(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Dapp(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Dapp(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Dapp(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Dapp> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Dapp.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Dapp> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Dapp.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Dapp> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Dapp.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Dapp> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Dapp.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public RemoteCall<BigInteger> balanceOf(String param0) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getBalance(String _userAddress) {
        final Function function = new Function(FUNC_GETBALANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_userAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getId(String _userAddress) {
        final Function function = new Function(FUNC_GETID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_userAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> idMap(BigInteger param0) {
        final Function function = new Function(FUNC_IDMAP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> registerUser(String _name) {
        final Function function = new Function(
                FUNC_REGISTERUSER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> topUpBalance(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_TOPUPBALANCE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> transfer(BigInteger _recipientID, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_recipientID), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> userCount() {
        final Function function = new Function(FUNC_USERCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<Boolean> userExists(String _userAddress) {
        final Function function = new Function(FUNC_USEREXISTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_userAddress)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Tuple2<BigInteger, String>> users(BigInteger param0) {
        final Function function = new Function(FUNC_USERS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteCall<Tuple2<BigInteger, String>>(
                new Callable<Tuple2<BigInteger, String>>() {
                    @Override
                    public Tuple2<BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, String>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    @Deprecated
    public static Dapp load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Dapp(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Dapp load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Dapp(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Dapp load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Dapp(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Dapp load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Dapp(contractAddress, web3j, transactionManager, contractGasProvider);
    }
}
