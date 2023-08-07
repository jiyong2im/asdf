import React, {useEffect, useState} from 'react';
import axios from "axios";
import "../components/BoardList.css"
import { Link, useNavigate } from 'react-router-dom';

const BoardList = () => {
    const navigate = useNavigate();
    const [boardList, setBoardList] = useState([]);
    const [boardPgn, setBoardPgn] = useState([]);
    const [pageNumber, setPageNumber] = useState();

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
    }, []);


    const moveToWrite = () => {
        navigate('/write');
    };
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
                        <h3><Link to={`/list/${board.number}`}>{board.number} / {board.title} </Link></h3>
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

