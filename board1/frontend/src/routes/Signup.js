import React, { useState } from 'react';
import axios from "axios";
import { useNavigate } from 'react-router-dom';

function Signup() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    name: '',
    uid: '',
    password: '',
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async () => {
    await axios.post(`/signup`, formData).then((res) => {
      alert('등록되었습니다.');
      navigate('/list');
    });
    // event.preventDefault();
    // console.log('Form submitted:', formData);
    // 여기서 서버로 데이터를 보내거나 다른 처리를 할 수 있습니다.
  };

  return (
    <div className="signup-form">
      <h2>회원가입</h2>
        <div className="form-group">
          <label htmlFor="uid">&nbsp;&nbsp;&nbsp;아이디 : </label>
          <input
              type="uid"
              id="uid"
              name="uid"
              value={formData.uid}
              onChange={handleChange}
              required
          />
        </div>
        <div className="form-group">
          <label htmlFor="name">사용자명 : </label>
          <input
            type="text"
            id="name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="password">비밀번호 : </label>
          <input
            type="password"
            id="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>
        <button onClick={handleSubmit}>가입하기</button>
    </div>
  );
}

export default Signup;