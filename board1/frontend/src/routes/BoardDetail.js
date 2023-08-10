import React, { useEffect, useState } from 'react';
import {Link, useParams, useNavigate} from 'react-router-dom';
import axios from 'axios';
import Board from '../components/Board';
import boardList from "./BoardList";

const BoardDetail = () => {
    const navigate = useNavigate();
    const {number } = useParams();
    const [loading, setLoading] = useState(true);
    const [board, setBoard] = useState({});
    const pi = 1;
    const [checked, setChecked] = useState();
    const checkedGreat = 1;
    const checkedHate = 2;

    const { title, writer, contents, views, createdAt, great, hate } = board;

    const moveToUpdate = () => {
        navigate('/update/' + number);
    };

    const deleteBoard = async () => {
        if (window.confirm('게시글을 삭제하시겠습니까?')) {
            await axios.delete(`/list/${number}`).then((res) => {
                alert('삭제되었습니다.');

                navigate('/list');
            });
        }
    };
    //useState() 를 먼저 적어 놨는데 안바뀐 상태로 api 요청하는지 모르겠다.
    const moveToGreat = async () => {
        await setChecked(1);
        axios.get(`/list/like/${number}?checked=${checkedGreat}`).then((res) =>{
            setBoard(res.data);
        });
    };

    const moveToHate = async () => {
        await setChecked(2);
        //${}리터럴 된 값 // 쓸 때 업데이트 됨
        axios.get(`/list/like/${number}?checked=${checkedHate}`).then((res) =>{
            setBoard(res.data);
        });
    };


    const moveToList = () => {
        navigate('/list');
    };

    const getData = async () => {
        axios.get(`/list/${number}?views=true`).then((res) =>{
            setBoard(res.data);
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
                <div>
                    <h4>글번호 : {number}</h4>
                    <hr />
                    <h4>제목 : {title}</h4>
                    <hr />
                    <h5>작성자 : {writer}</h5>
                    <hr />
                    <h5>조회수 : {views}</h5>
                    <hr />
                    <h5>최초작성 : {createdAt}</h5>
                    <hr />
                    <p>본문 : {contents}</p>
                </div>
            <button onClick={moveToGreat}>👍🏼{great}</button>&nbsp;&nbsp;&nbsp;&nbsp;<button onClick={moveToHate}>👎🏾 {hate}</button>

            <div>
                <button onClick={moveToUpdate}>수정</button>
                <button onClick={deleteBoard}>삭제</button>
                <button onClick={moveToList}>목록</button>
            </div>
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
// {/*)}*/}
// const getBoard = async () => {
//     const resp = await (await axios.get(`/list/${number}`)).data;
//     setBoard(resp.data);
//     setLoading(false);
//     console.log(resp);
// };
//         <div>
//             {Object.values(board).map((dd) => (
//                 <div>
//                     <h4>글번호 : {number}</h4>
//                     <hr />
//                     <h5>작성자 : {dd.writer}</h5>
//                     <hr />
//                     <p>본문 : {dd.contents}</p>
//
//                 </div>
//             ))}
//         </div>
//     );
// };