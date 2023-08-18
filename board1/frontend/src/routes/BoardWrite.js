import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from 'react-router-dom';
import axios from 'axios';

const BoardWrite = () => {
    const navigate = useNavigate();


    const [board, setBoard] = useState({
        title: '',
        writer:'',
        contents: '',
    });

    const [userInfo, setUserInfo] = useState({});


    const { title, writer, contents } = board;


    const onChange = (event) => {
        const { value, name } = event.target;
        setBoard({
            ...board,
            [name]: value,
        });
    };

    const saveBoard = async () => {
        await axios.post(`/list`, board).then((res) => {
            alert('등록되었습니다.');
            navigate('/list');
        });
    };

    const backToList = () => {
        navigate('/list');
    };

    const getInfo = async () => {
        await axios.get(`/checkedLogin`).then((res) =>{
            setUserInfo(res.data);
            console.log(res);
            console.log('성공');
        });
    };
    const change = () => {
        const {value, writer} = userInfo.username;
        setBoard({
            ...board,
            [writer]: value,
        })
    };
    useEffect(() => {
        getInfo();
    }, []);


    useEffect(() => {
        if (userInfo.username) {
            setBoard((prevBoard) => ({
                ...prevBoard,
                writer: userInfo.username,
            }));
        }
    }, [userInfo]);

    return (
        <div>
            <div>
                <span>제목</span>
                <input type="texwt" name="title" value={title} onChange={onChange} />
            </div>
            <br />
            <div>
                <span>작성자</span>
                <input
                    type="text"
                    name="writer"
                    value={writer}
                    readOnly={true}
                    onInput={onChange}
                />

            </div>
            <br />
            <div>
                <span>내용</span>
                <textarea
                    name="contents"
                    cols="30"
                    rows="10"
                    value={contents}
                    onChange={onChange}
                ></textarea>
            </div>
            <br />
            <div>
                <button onClick={saveBoard}>저장</button>
                <button onClick={backToList}>취소</button>
            </div>
        </div>
    );
};

export default BoardWrite;