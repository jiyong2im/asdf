import React, {useEffect, useState} from 'react';
import axios from "axios";
import "../components/BoardList.css"
import { Link, useNavigate } from 'react-router-dom';

const BoardList = () => {
    const navigate = useNavigate();
    const [boardList, setBoardList] = useState([]);
    const [boardPgn, setBoardPgn] = useState([]);
    const [pageNumber, setPageNumber] = useState();
    const [userInfo, setUserInfo] = useState({});

    const getData = async (pgn= 1) => {
        axios.get(`/list?pageNo=` + pgn)
            .then((res) =>{
                setBoardList(res.data.dto);
                setBoardPgn(res.data.pgn);
                setPageNumber(pgn);
            });
    };

    useEffect(() => {
        getData();
        check();

    }, []);

    const moveToWrite = () => {
        if(Object.is(userInfo.username, undefined)){
            console.log('username : null');
            alert('로그인이 필요합니다.');
            navigate('/login');
        }else {
            console.log('username : notnull');
            navigate('/write');
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
                <table border="1">
                    <th className={"num"}>번호</th>
                    <th className={"title"}>제목</th>
                    <th className={"writer"}>작성자</th>
                    <th className={"writer"}>조회수</th>
                    <th className={"regtime"}>업데이트</th>

                        {boardList && boardList.map((board) => (
                            <tr>
                                <td >{board.number}</td>
                                <td ><h3><Link to={`/list/${board.number}`}>{board.title}</Link></h3></td>
                                <td>{board.id}</td>
                                <td>{board.views}</td>
                                <td>{board.updatedAt}</td>
                            </tr>
                        ))}
                </table>
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

