import { Route, Routes } from 'react-router-dom';
import BoardList from './routes/BoardList';
import Home from './routes/Home';
import React from 'react';
import BoardDetail from './routes/BoardDetail';
import BoardWrite from './routes/BoardWrite';
import BoardUpdate from "./routes/BoardUpdate";
import Login from "./routes/Login";
import Signup from "./routes/Signup";
import Search from "./routes/Search";


function App() {
    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/list" element={<BoardList />} />
            <Route path="/list/:number" element={<BoardDetail />} />
            <Route path="/write" element={<BoardWrite />} />
            <Route path="/login" element={<Login/>} />
            <Route path="/signup" element={<Signup/>} />
            <Route path="/search/:word" element={<Search/>} />
            <Route path="/update/:number" element={<BoardUpdate />} />
        </Routes>
    );
}

export default App;