import React, { useEffect, useState } from 'react';
import {Link, useParams, useNavigate} from 'react-router-dom';
import axios from 'axios';
import boardList from "./BoardList";

const BoardDetail = () => {
    const navigate = useNavigate();
    const {number} = useParams();
    const [board, setBoard] = useState({});
    const [checked, setChecked] = useState();
    const [checked2, setChecked2] = useState();
    const [userInfo, setUserInfo] = useState({});
    const username = userInfo.username;
    const [commentList, setCommentList] = useState([])
    const [pageNumber, setPageNumber] = useState();
    const [boardPgn, setBoardPgn] = useState([]);

    const [comment, setComment] = useState({
        content: '',
    });
    const  content  = comment.content;
    const {title, writer, contents, views, createdAt, great, hate} = board;

    const moveToUpdate = async () => {
            if (Object.is(username, writer)) {
                navigate('/update/' + number);
            } else {
                alert('권한이 없습니다.');
            }
    };

    const check = async () => {
        await axios.get(`/checkedLogin`).then((res) => {
            setUserInfo(res.data);
            console.log('하이 ' + res.data.username);
            console.log('헬로 ' + writer);
        });
    };

        const deleteBoard = async () => {
                if (Object.is(username, writer)) {
                    if (window.confirm('게시글을 삭제하시겠습니까?')) {
                        axios.delete(`/list/${number}`).then((res) => {
                            alert('삭제되었습니다.');
                            navigate('/list');
                        });
                    }
                }
                else {
                    alert('권한이 없습니다.');
                }
        };

        const moveToGreat = async () => {
            if (Object.is(username, undefined)) {
                alert('로그인이 필요합니다.');
            }else {
                if(checked){
                    axios.get(`/list/like/${number}?checked=1&username=` + username).then((res) => {
                    setBoard(res.data);
                    setChecked(false);
                    });
                }else {
                    alert('1회 제한입니다.');
                }
            }
        };

        const moveToHate = async () => {
            if (Object.is(username, undefined)) {
                alert('로그인이 필요합니다.');
            }else {
                if(checked2) {
                        //${}리터럴 된 값 // 쓸 때 업데이트 됨
                        axios.get(`/list/like/${number}?checked=2&username=` + username).then((res) => {
                        setBoard(res.data);
                        setChecked2(false);
                    });
                } else {
                    alert('1회 제한입니다.');

                }
            }
        };


        const moveToList = () => {
            navigate('/list');
        };

        const getData = async () => {
            axios.get(`/list/${number}?views=true`).then((res) => {
                setBoard(res.data);
                console.log(writer);
                console.log('dafasd' + writer);
                console.log('성공');
                setChecked(true);
                setChecked2(true);
            });
        };
    const getComment = async (pgn =1) => {
        await  axios.get(`/comment?number=` + number+`&pageNo=`+pgn)
            .then((res) =>{
                console.log('이것은 로구asdf' + res);
                console.log('이것은 로구asdf' + res.data.dto);
                setBoardPgn(res.data.pgn)
                setCommentList(res.data.dto);
                setPageNumber(pgn);
            });
    };

        useEffect(() => {
            getData();
            getComment();
            check();

        }, []);
    const onChange = (event) => {
        const { value, name } = event.target;
        setComment({
            ...comment,
            [name]: value,
        });
    };

    const insertComment = async () => {
        if (Object.is(username, undefined)) {
            alert('로그인이 필요합니다.');
        }else {
            let list = {content, username, number}
            console.log('이것은 로구' + list.username);
            console.log('이것은 로구' + list.content);
            console.log('이것은 로구' + list.number);
            await axios.post(`/comment`, list).then((res) => {
                alert('댓글이 등록되었습니다.');
                // window.location.reload();
                //getComment();
            }).catch(()=>{alert('오류s')});
        }
    };

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
    const movePagination =(pgn) => {
        getComment(pgn);
    }
    const buttonClick =(com) =>{
        if (Object.is(username, com.username)) {
            axios.delete(`/comment?number=` + com.number).then((res) => {
                alert('삭제되었습니다.');
                getComment();
                return <div></div>
            });
        }else {
            alert('로그인 정보가 다릅니다.');
        }
    }


    return (
            <div>
                <div>
                    <h4>글번호 : {number}</h4>
                    <hr/>
                    <h4>제목 : {title}</h4>
                    <hr/>
                    <h5>작성자 : {writer}</h5>
                    <hr/>
                    <h5>조회수 : {views}</h5>
                    <hr/>
                    <h5>최초작성 : {createdAt}</h5>
                    <hr/>
                    <p>본문 : {contents}</p>
                </div>
                <button onClick={moveToGreat}>👍🏼{great}</button>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <button onClick={moveToHate}>👎🏾 {hate}</button>
                <div>
                    <button onClick={moveToUpdate}>수정</button>
                    <button onClick={deleteBoard}>삭제</button>
                    <button onClick={moveToList}>목록</button>
                </div>
                <ul>
                    <h4>댓글</h4>
                    <input type="text" name="content" onChange={onChange} placeholder="" />
                    <button onClick={()=>{insertComment();getComment();}}>댓글추가</button>
                    <li>
                        {commentList && commentList.map((com) => (
                            <ul key={com.number}>
                                <h3>{com.number},{com.username},{com.content} <button onClick={()=>buttonClick(com)}>X</button></h3>
                                <hr/>
                            </ul>

                        ))}
                    </li>
                </ul>
                <div>
                    {pagination()}
                </div>
            </div>
        );
};
export default BoardDetail;
