import React, { useState } from 'react';
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
    //
    //
    // const handlePasswordChange = (event) => {
    //     setPassword(event.target.value);
    // };


    const handleSubmit = async () => {
        let form = new FormData();
        form.append("username", login.username);
        form.append("password",login.password);

        await axios.post(`/login`, form).then((res) => {
            console.log('Form submitted:', form);
            alert('로그인이 완료 되었습니다.');
        });
    };

    return (
        <div className="App">
                <div className="form-group">
                    <label>Username</label>
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
                    <label>Password</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value={login.password}
                        onChange={handleUsernameChange}
                        required
                    />
                </div>
                <button onClick={handleSubmit}>Log In</button>
        </div>
    )

    }
export default Login;
