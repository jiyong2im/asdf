import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from "axios";

function Login() {
    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = (event) => {
        const saveBoard = async () => {
            await axios.post(`/login`, event).then((res) => {
                alert('로그인이 완료 되었습니다.');
                navigate('/list');
            });
        };

        event.preventDefault();
        console.log('Submitted:', { username, password });
    };

    return (
        <div className="App">
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Username</label>
                    <input
                        type="text"
                        value={username}
                        onChange={handleUsernameChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Password</label>
                    <input
                        type="password"
                        value={password}
                        onChange={handlePasswordChange}
                        required
                    />
                </div>
                <button type="submit">Log In</button>
            </form>
        </div>
    );
}

export default Login;
