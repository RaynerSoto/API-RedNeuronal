import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'


createRoot(document.getElementById('root')).render(
  <StrictMode>
      <div className="container mt-5">
          <h1 className="text-center text-blue-500">Hola Rayner</h1>
          <button className="btn btn-primary">Bootstrap</button>
      </div>
  </StrictMode>,
)


