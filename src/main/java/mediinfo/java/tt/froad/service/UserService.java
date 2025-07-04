package mediinfo.java.tt.froad.service;


import mediinfo.java.tt.froad.dtos.Rso;
import mediinfo.java.tt.froad.dtos.user.AddUserDto;
import mediinfo.java.tt.froad.dtos.user.GetUserDto;

public interface UserService {

    Rso<GetUserDto> addUser(AddUserDto addUserDto);
}
