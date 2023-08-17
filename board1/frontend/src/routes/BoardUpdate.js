import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';

const BoardUpdate = () => {
    const navigate = useNavigate();
    const { number } = useParams(); // /update/:idx와 동일한 변수명으로 데이터를 꺼낼 수 있습니다.
    const [board, setBoard] = useState({
        number: 0,
        title: '',
        writer: '',
        contents: '',
    });

    const { title, writer, contents } = board;

    const onChange = (event) => {
        const { value, name } = event.target; //event.target에서 name과 value만 가져오기
        setBoard({
            ...board,
            [name]: value,
        });
    };

    const getData = async () => {
        axios.get(`/list/${number}?views=false`).then((res) =>{
            setBoard(res.data);
            console.log(res);

        });
    };

    const updateBoard = async () => {
        await axios.patch(`/list/${number}?views=false`, board).then((res) => {
            alert('수정되었습니다.');
            navigate('/list');
        });
    };

    const backToDetail = () => {
        navigate('/list/' + number);
    };

    useEffect(() => {
        getData();
    }, []);

    return (
        <div>
            <div>
                <span>제목</span>
                <input type="text" name="title" value={title} onChange={onChange} />
            </div>
            <br />
            <div>
                <span>작성자</span>
                <input type="text" name="writer" value={writer} readOnly={true} />
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
                <button onClick={updateBoard}>수정</button>
                <button onClick={backToDetail}>취소</button>
            </div>
        </div>
    );
};

export default BoardUpdate;