pragma solidity >=0.4.22 <0.7.0;
pragma experimental ABIEncoderV2;


contract Chore {
    
    mapping (address => chore[] ) chores;
    mapping (address => uint256) counter;    
    
    struct chore {
        uint256 id;
        string name;
        address user;
        uint256 pointsPerX;
    }
    

    uint256 number;

    function addChore(string memory choreName, uint256 pointsPerX) public {
        require(checkIfNameExists(choreName), "duplicate choreName");
        counter[msg.sender] =  counter[msg.sender] + 1;
        uint256 _counter = counter[msg.sender] ;
        chore memory _chore = chore (
            _counter,
            choreName,
            msg.sender,
            pointsPerX
            );
        chores[msg.sender].push(_chore);
    }
    
    function checkIfNameExists (string memory name) internal view returns (bool) {
        for (uint i=0; i<chores[msg.sender].length; i ++) {
           if (hashCompareWithLengthCheck(name,chores[msg.sender][i].name)){
               return false;}
           }
           return true;
    }
    
    
    function getChores() public view returns (chore[] memory) {
        return chores[msg.sender];
    }
    
    function hashCompareWithLengthCheck(string memory a, string memory b) internal pure returns (bool) {
        if(bytes(a).length != bytes(b).length) {
        return false;
        } else {
            return keccak256(bytes(a)) == keccak256(bytes(b));
        }
    }
    
    function addChoreExecution(uint _id) public {
        
    }



}


