import React, {useEffect, useState} from 'react';
import axios from "axios";
import { Link, useNavigate } from 'react-router-dom';

const BoardList = () => {
    const navigate = useNavigate();
    const [boardList, setBoardList] = useState([]); // 초기값으로 빈 배열 설정

    const getData = async () => {
        axios.get('/list')
            .then((res) =>{
                setBoardList(res.data);
                console.log(BoardList);

                console.log('성공');
            });
    };

    useEffect(() => {
        getData();
    }, []);


    const moveToWrite = () => {
        navigate('/write');
    };

    return (
        <div>
            <ul>
                {boardList && boardList.map((board) => (
                    <li key={board.number}>
                        <Link to={`/list/${board.number}`}>{board.number} {board.title} </Link>
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


// const getBoardList1 = async () => {
//     try {
//         const response = await axios.get('/list');
//         setBoardList(response.data.data);
//     } catch (error) {
//         console.error("Error fetching board list:", error);
//     }
// }

// ID로 사용자 요청
//     useEffect( ()=> {
//     axios.get('/list')
//         // 응답(성공)
//         .then(function (response) {
//             setBoardList(response.data); // 3) boardList 변수에 할당
//
//             console.log(response);
//         })
//         },[])
//     async function axiosExample() {
//         const response = await axios.get('/list');
//         const { data } = response;
//     }
//
// useEffect( ()=> {
//      axios.get('/list').then((res)=>{
//         setBoardList(res.data)
//         console.log(res)
//     })
// },[])
// const fetchData = async () => {
//     try {
//         const response = await axios.get('/list');
//         setBoardList(response.data.data);
//         console.log(response.data.data);
//
//         console.log('데이터 가져오기 성공');
//     } catch (error) {
//         console.error("데이터 가져오기 오류:", error);
//     }
// }
{/*{OrderedArray.map((data) => (*/}
{/*    {Object.entries(data.orderDetails).map(([key, value]) => (*/}
{/*            <div>*/}
{/*                <div>{key}</div>*/}
{/*                <div>{value}EA</div>*/}
{/*            </div>*/}
{/*        )))));*/}
{/*{boardList.map(board) => (*/}
{/*{board.map(bod) => {(*/}
{/*    <li key={bod.number}>*/}

{/*    )}}*/}
{/*    )}*/}
// const getBoardList = async () => {
//     const resp = await (await axios.get('/list')).data;
//     setBoardList(resp.data); // 3) boardList 변수에 할당
//     // 2) 게시글 목록 데이터에 할당
//     // const pngn = resp.pagination;
//     // console.log(pngn);
//     console.log(boardList);
//     console.log(resp.data);
// }