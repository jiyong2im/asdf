package com.example.board1.data.boardEntity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")

@Getter
@Setter
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String uid;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles =new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Override
    public String getUsername() {
        return this.uid;
    }
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Override
    public String getPassword() {
        return this.password;
    }


    @JsonProperty(access = JsonProperty.Access.READ_WRITE)

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)

    @Override
    public boolean isEnabled() {
        return true;
    }


//
//    @Column(nullable = false, unique = true)
//    private String Id;
//
//    @Column(nullable = false)
//    private String Name;
//
//    @Column(nullable = false)
//    private String password;

//
//    @OneToMany(mappedBy = BaseEntity)
//    @JoinColumn(name = "boardentity_number")
//    private ArrayList<BoardEntity> boardEntity = new ArrayList<>();

}
