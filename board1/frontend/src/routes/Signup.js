import React, { useState } from 'react';

function Signup() {
  const [formData, setFormData] = useState({
    username: '',
    id: '',
    password: '',
  });

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log('Form submitted:', formData);
    // 여기서 서버로 데이터를 보내거나 다른 처리를 할 수 있습니다.
  };

  return (
    <div className="signup-form">
      <h2>회원가입</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="email">&nbsp;&nbsp;&nbsp;아이디 : </label>
          <input
              type="id"
              id="id"
              name="id"
              value={formData.id}
              onChange={handleChange}
              required
          />
        </div>
        <div className="form-group">
          <label htmlFor="username">사용자명 : </label>
          <input
            type="text"
            id="username"
            name="username"
            value={formData.username}
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
        <button type="submit">가입하기</button>
      </form>
    </div>
  );
}

export default Signup;