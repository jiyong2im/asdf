import React from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Board = ({number, title , contents, writer }) => {
    const navigate = useNavigate();
    console.log(title);

    const moveToUpdate = () => {
        navigate('/update/' + number);
    };

    const deleteBoard = async () => {
        if (window.confirm('게시글을 삭제하시겠습니까?')) {
            await axios.delete(`/lsit/${number}`).then((res) => {
                alert('삭제되었습니다.');
                navigate('/list');
            });
        }
    };

    const moveToList = () => {
        navigate('/list');
    };

    return (
        <div>
            <div>
                <h2>{title}</h2>
                <h5>{writer}</h5>
                <hr />
                <p>{contents}</p>
            </div>
            <div>
                <button onClick={moveToUpdate}>수정</button>
                <button onClick={deleteBoard}>삭제</button>
                <button onClick={moveToList}>목록</button>
            </div>
        </div>
    );
};

export default Board;