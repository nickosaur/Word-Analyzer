import React from 'react'
import { Link } from 'react-router-dom'


const Home = () => {
    return (
        <div className="container">
            <div className="row justify-content-center">
                <div className="col-md-3">
                    <div className="card mb-4 shadow-sm">
                        <div className="card-body">
                            <p className="card-text text-center">Analyze your text file!</p>
                            <Link to="/upload" className="btn btn-sm btn-block btn-outline-secondary">Click here to upload</Link>
                        </div>
                    </div>
                </div>
                <div className="col-md-3">
                    <div className="card mb-4 shadow-sm">
                        <div className="card-body">
                            <p className="card-text text-center">Get Results List</p>
                            <Link to="/results" className="btn btn-sm btn-block btn-outline-secondary">Click here to view</Link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Home