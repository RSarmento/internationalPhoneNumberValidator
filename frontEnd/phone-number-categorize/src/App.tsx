import React, {useState} from 'react';
import './App.css';
import List from "./components/List";
import {Phone} from "./model/phone";

function App() {
    const itemsMock: Phone[] = [
        {countryName: "AZIA", countryCode: "666", state: true, number: 237846523},
        {countryName: "BAKNA", countryCode: "777", state: false, number: 95678923}]
    const[searchValue, setSearchValue] = useState("")
    const[isValid, setIsValid] = useState(false)
    return (
        <div>
            <h1>International Phone Number Categorize</h1>
            <input type="text" placeholder={"Country here"} value={searchValue} onChange={(e) => setSearchValue(e.target.value)}/>
            <select name="state" value={isValid?1:0} onChange={(e) => setIsValid(!!e.target.value)}>
                <option value={1}>
                    Valid
                </option>
                <option value={0}>
                    Invalid
                </option>
            </select>
            <div>
                <span> COUNTRY NAME </span>
                <span> COUNTRY CODE </span>
                <span> PHONE NUMBER </span>
                <span> STATE </span>
            </div>
            <List items={itemsMock}/>
        </div>
    );
}
export default App;
