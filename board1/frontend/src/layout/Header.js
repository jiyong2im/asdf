import React from 'react';
import {Link} from "react-router-dom";

const Header = () => {
    return (
        <header>
            <Link to="/">홈</Link>
            &nbsp;&nbsp; | &nbsp;&nbsp;
            <Link to="/list">게시판</Link>
            &nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <Link to="/list">로그인</Link>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <Link to="/list">회원가입</Link>
            <hr/>
        </header>
    );
};

export default Header;