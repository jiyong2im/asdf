import React, {useEffect, useState} from 'react';
import {Link, useNavigate} from "react-router-dom";
import axios from "axios";

const Header = () => {
    const navigate = useNavigate();
    const [userInfo, setUserInfo] = useState({});
    const [toggle, setToggle] = useState({});
    const [search, setSearch] = useState({
        word: '',
    });
    const  word  = search.word;

    const logoutOnclick = async () => {
        await axios.get(`/logout`).then((res) => {
            alert('로그아웃이 완료 되었습니다.');
            setToggle(true);
            navigate('/home');
        });
    };
    const onChange = (event) => {
        const { value, name } = event.target;
        console.log(event.target);
        setSearch({
            ...search,
            [name]: value,
        });
        console.log(search);

    };
    const getInfo = async () => {
             await axios.get(`/checkedLogin`).then((res) =>{
                setUserInfo(res.data);
                console.log(res);
                 setToggle(false);
                console.log('성공');
            });
        };

    useEffect(() => {
        getInfo();
    }, [toggle]);

    const goToSearch = () => {
        console.log('이것은 로구' );
        console.log('goToSearch' + search.word);
        navigate('/search/' + word);
    };

    function checkLogin() {

        if(userInfo.username != null){
            return  <b>
                        {userInfo.username}님&nbsp;&nbsp;&nbsp;&nbsp;
                        <Link to="/logout" onClick={logoutOnclick}>로그아웃</Link>
                    </b>
        }
        return <Link to="/login">로그인</Link>
    }

    return (
        <header>
            <Link to="/">홈</Link>
            &nbsp;&nbsp; | &nbsp;&nbsp;
            <Link to="/list">게시판</Link>
            &nbsp;&nbsp;&nbsp;&nbsp;
            {checkLogin()}
            &nbsp;&nbsp;&nbsp;&nbsp;
            <Link to="/signup">회원가입</Link>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="word" name="word" onChange={onChange} placeholder="검색" />
            <button onClick={goToSearch}>검색</button>
            <hr/>
        </header>
    );
};

export default Header;