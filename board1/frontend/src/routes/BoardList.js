import React, {useEffect, useState} from 'react';
import axios from "axios";
import { Link, useNavigate } from 'react-router-dom';

const BoardList = () => {
    const navigate = useNavigate();
    const [boardList, setBoardList] = useState([[]]); // 초기값으로 빈 배열 설정

    const getBoardList = async () => {
        const resp = await (await axios.get('/list')).data; // 2) 게시글 목록 데이터에 할당
        setBoardList(resp.data); // 3) boardList 변수에 할당


        // const pngn = resp.pagination;
        // console.log(pngn);
        console.log(resp.data);
        console.log(BoardList);

    }


    useEffect(() => {
        getBoardList(); // 1) 게시글 목록 조회 함수 호출
    }, []);


    //
    // useEffect( ()=> {
    //      axios.get('/list').then((res)=>{
    //         setBoardList(res.data)
    //         console.log(res)
    //     })
    // },[])

    const moveToWrite = () => {
        navigate('/write');
    };


    return (
        <div>
            <ul>

                {boardList && boardList.map((board) => ( // Use 'board' instead of 'a'
                    <li key={board.number}>
                        <Link to={`/board/${board.number}`}>{board.number} {board.title} </Link>
                    </li>
                ))}
            </ul>
            <div>
                <button onClick={moveToWrite}>글쓰기</button>
            </div>
        </div>
    );
};

export default BoardList;
