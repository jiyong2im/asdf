import React, { useEffect, useState } from 'react';
import {Link, useParams} from 'react-router-dom';
import axios from 'axios';
import Board from '../components/Board';
import boardList from "./BoardList";

const BoardDetail = () => {
    const {number } = useParams(); // /board/:idx와 동일한 변수명으로 데이터를 꺼낼 수 있습니다.
    const [loading, setLoading] = useState(true);
    const [board, setBoard] = useState({});
    const pi = 1;

    const getBoard = async () => {
        const resp = await (await axios.get(`/list/${number}`)).data;
        setBoard(resp.data);
        setLoading(false);
        console.log(resp);
    };
    const getData = async () => {
        axios.get(`/list/${number}`).then((res) =>{
                setBoard(res);
                console.log(res);

                console.log('성공');
            });
    };
    //초기에 한번 실행하는 hook
    useEffect(() => {
        getData();

    }, []);

    return (
        <div>
            {
                {pi} === 1
                    ? {Object.values(board).map((dd) => (
                            <div>
                                <h4>{number}</h4>
                                <hr />
                                <h5>{dd.writer}</h5>
                                <hr />
                                <p>{dd.contents}</p>

                            </div>
                        ))}
                    : null


            }

        </div>
    );
};

export default BoardDetail;



{/*{loading ? (*/}
{/*    <h2>loading...</h2>*/}
{/*) : (*/}

{/*    <Board*/}
{/*        number={number}*/}
{/*        title={board.title}*/}
{/*        contents={board.contents}*/}
{/*        name={board?.writer}*/}
{/*    />*/}
{/*)}*/}