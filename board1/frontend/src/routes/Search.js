import React, {useEffect, useState} from 'react';
import axios from "axios";

import {Link, useNavigate, useParams} from 'react-router-dom';

const BoardList = () => {
    const navigate = useNavigate();
    const [boardList, setBoardList] = useState([]);
    const [boardPgn, setBoardPgn] = useState([]);
    const {word} = useParams();
    const [pageNumber, setPageNumber] = useState();
    const [userInfo, setUserInfo] = useState({});

    const getData = async (pgn= 1) => {
        console.log('여기선 어떤 값이', word )
        axios.get(`/search?word=` + word)
            .then((res) =>{
                setBoardList(res.data);
            });
    };

    useEffect(() => {
        getData();
        check();

    }, [word]);// 여기 바낄 떄마다

    const moveToWrite = () => {
        if(userInfo.username != null){
            console.log('username : notnull');
            navigate('/write');
        }else {
            console.log('username : null');
            alert('로그인이 필요합니다.');
            navigate('/login');
        }
    };
    const check = async () =>{
        await axios.get(`/checkedLogin`).then((res) =>{
            setUserInfo(res.data);
            console.log(res);
            console.log('성공');
        });
    }
    const movePagination =(pgn) => {
        getData(pgn)
    }
    const pagination = () => {
        var array =[]
        for(let i=0; i < boardPgn.length; i++){
            if(pageNumber === boardPgn[i].pageNo){
                array.push(<button className={"button"} onClick={() => movePagination(boardPgn[i].pageNo)}>{boardPgn[i].display}</button>)
            }else {
                array.push(<button onClick={() => movePagination(boardPgn[i].pageNo)}>{boardPgn[i].display}</button>)
            }
        }
        return array
    }

    return (
        <div>
            {boardList && boardList.map((board) => (
                <ul key={board.number}>
                    <h3><Link to={`/list/${board.number}`}>N : {board.number} /T : {board.title} /V : {board.views} /U : {board.updatedAt}</Link></h3>
                    <hr />
                </ul>
            ))}
            <div>
                <button onClick={moveToWrite}>글쓰기</button>
            </div>
            <div>
                {pagination()}
            </div>
        </div>
    );
};

export default BoardList;

