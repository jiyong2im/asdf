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
        await axios.post(`/login`, login).then((res) => {
            console.log('Form submitted:', login);
            alert('로그인이 완료 되었습니다.');

            // navigate('/list');
        });
    };

    return (
        <div className="App">
                <div className="form-group">
                    <label>Username</label>
                    <input
                        type="text"
                        id="uid"
                        name="uid"
                        value={login.uid}
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
