import React, { useState,useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from "axios";

function Login() {
    const navigate = useNavigate();

    const [login, setLogin] = useState({
        uid: '',
        password:'',
    });

    const handleUsernameChange = (event) => {
        const { name, value } = event.target;
        setLogin({
            ...login,
            [name]: value,
        });
    };

    const handleSubmit = async () => {
        let form = new FormData();
        form.append("username", login.username);
        form.append("password",login.password);

        await axios.post(`/login`, form).then((res) => {
            console.log('Form submitted:', res);
            if(Object.is(res.data ,undefined )){
                console.log('dsadsa:', res.data);
                alert('회원정보 불일치')
            }else{
                window.location.href='/home'
                alert('로그인이 완료 되었습니다.');
            }
        });
    };

    return (
        <div className="App">
                <div className="form-group">
                    <label>아이디</label>
                    <input
                        type="text"
                        id="uid"
                        name="username"
                        value={login.username}
                        onChange={handleUsernameChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>비밀번호</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={login.password}
                        onChange={handleUsernameChange}
                        required
                    />
                </div>
                <button onClick={handleSubmit}>로그인</button>
        </div>
    )

    }
export default Login;
