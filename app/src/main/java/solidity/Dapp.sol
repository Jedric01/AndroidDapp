pragma solidity ^0.5.1;

contract Dapp{
    struct User{
        uint id;
        string name;
    }

    mapping(address => uint256) public balanceOf;
    mapping(uint => address) public idMap;

    User[] public users;
    uint256 public userCount = 0;

    constructor() public{
        balanceOf[msg.sender] = 20;
        idMap[userCount++] = msg.sender;
    }

    function userExists(address _userAddress) public view returns(bool){
        for(uint i = 0; i < userCount; i++){
            if(idMap[i] == _userAddress)
                return true;
        }
        return false;
    }

    function registerUser(string memory _name) public returns(bool) {
        //todo check for registered users
        if(userExists(msg.sender))
            return false;
        idMap[userCount] = msg.sender;
        users.push(User(userCount++, _name));

        return true;
    }

    function topUpBalance() public payable{
        if(!userExists(msg.sender))
            revert();
        if(msg.value < 50)
            revert();

        balanceOf[msg.sender] += 5;
    }

    function transfer(uint256 _recipientID, uint256 _value) public returns(bool) {
        if(!userExists(msg.sender) || !userExists(idMap[_recipientID]))
            revert();
        if(balanceOf[msg.sender] < _value)
            return false;
        balanceOf[msg.sender] -= _value;
        balanceOf[idMap[_recipientID]] += _value;
        // emit Transfer(msg.sender, _to, _value);
        return true;
    }

    function getBalance(address _userAddress) public view returns(uint256){
        if(!userExists(_userAddress))
            revert();
        return balanceOf[_userAddress];
    }

    function getId(address _userAddress) public view returns(uint256){
        for(uint i = 0; i < userCount; i++){
            if(idMap[i] == _userAddress)
                return i;
        }
    }
}