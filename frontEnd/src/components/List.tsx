import Item from "./Item";
import {Phone} from "../model/phone";

type Props = {
    items: Phone[]
}
const list = ({items}: Props) => {
    return (
        <div className="self-stretch">
            {items.map(item => {
                return (
                    <Item item={item}/>
                )
            })}
        </div>
    )
}
export default list
