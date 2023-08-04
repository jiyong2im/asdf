import { Route, Routes } from 'react-router-dom';
import BoardList from './routes/BoardList';
import Home from './routes/Home';
import React from 'react';
import BoardDetail from './routes/BoardDetail';
import BoardWrite from './routes/BoardWrite';
import BoardUpdate from "./routes/BoardUpdate";

function App() {
    return (
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/list" element={<BoardList />} />
            <Route path="/list/:number" element={<BoardDetail />} />
            <Route path="/write" element={<BoardWrite />} />
            <Route path="/update/:idx" element={<BoardUpdate />} />
        </Routes>
    );
}

export default App;