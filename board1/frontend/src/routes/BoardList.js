import React, {useEffect, useState} from 'react';
import axios from "axios";
import { Link, useNavigate } from 'react-router-dom';

const BoardList = () => {
    const navigate = useNavigate();
    const [boardList, setBoardList] = useState([]);

    const getBoardList = async () => {
        const resp = await (await axios.get('/list')).data; // 2) 게시글 목록 데이터에 할당
        setBoardList(resp.data); // 3) boardList 변수에 할당

        const pngn = resp.pagination;
        console.log(pngn);
        console.log(resp.data);

    }

    useEffect(()=> {
        axios.get('/list').then((res)=>{
            setBoardList(res.data)
            console.log(res)
        })
    },[])

    const moveToWrite = () => {
        navigate('/write');
    };
    //
    // const moveToWrite = () => {
    //     return navigate("/write");
    // };

    // useEffect(() => {
    //     getBoardList(); // 1) 게시글 목록 조회 함수 호출
    // }, []);

    return (
        <div>
            <ul>
                {
                    // {boardList.map(e => (
                    //         <UserName key={e.id}>{e.userName}</UserName>))}
                    // 키 할당 안함
                    boardList.map(function(a,i){
                        return(
                            <li>
                                <Link to={`/board/${boardList.number}`}>{boardList[i].number} {boardList[i].title}</Link>
                            </li>
                        )
                    })
                }
            </ul>
            <div>
                <button onClick={moveToWrite}>글쓰기</button>
            </div>
        </div>
    );
};

export default BoardList;
{/*{boardList && boardList.map((board) => (*/}
{/*    // 4) map 함수로 데이터 출력*/}
{/*    <li key={board.number}>{board.id} {board.name}</li>*/}
{/*))}*/}
{/*게시판 ----*/}