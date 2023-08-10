import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const BoardWrite = () => {
    const navigate = useNavigate();

    const [board, setBoard] = useState({
        title: '',
        writer:'',
        contents: '',
    });

    const { title, writer, contents } = board; //비구조화 할당

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
                    onChange={onChange}
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